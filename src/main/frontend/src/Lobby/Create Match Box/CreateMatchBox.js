import React, {Component} from 'react';
import { Button, Modal, ModalHeader, ModalBody, Form, FormGroup, Label, Input, InputGroup } from 'reactstrap';
import { Table, TableRow, TableCell, TableHead, TableBody } from "@material-ui/core";
import CheckIcon from '@material-ui/icons/Check';

export default class CreateMatchBox extends Component{
    constructor(props){
        super(props)
        this.state = {
            showCreateMatch: false,
            playerID: "",
            submitted: null,
            listOfPlayers: [],
            searchResults: [],
            failedInvites: []
        };

        this.toggle = this.toggleCM.bind(this);
        this.changePlayer = this.changePlayer.bind(this);
        this.startMatch = this.startMatch.bind(this);
    }

    changePlayer(event){
        let player = event.target.value;
        if(player.length > 0){
            fetch("/getUser?player="+player)
                .then(res => res.json())
                .then(data =>{
                    data.playersInDB = data.playersInDB.filter(player => player !== this.props.currentUser)
                    this.setState({searchResults: data.playersInDB})
            });
        }
        this.setState({playerID: player});
    }

    toggleCM(){
        let setCM = !this.state.showCreateMatch;
        this.setState({showCreateMatch: setCM});
    }

    startMatch(){
        fetch("/createMatch/?playerID="+ this.state.listOfPlayers.join(",") + "&current="+this.props.currentUser)
            .then(res => res.json())
            .then(data =>{
                if(data.gameStarted && data.opponent !== ""){
                    this.props.toGame();
                }
            });
    }

    sendInvite(player){
        if(player !== this.props.currentUser){
            fetch("./sendInvite/?player=" + player + "&current=" + this.props.currentUser)
                .then(res => res.json())
                .then(data =>{
                    if(data.foundPlayer){
                        let currentPlayers = this.state.listOfPlayers;
                        currentPlayers = currentPlayers.concat(data.sentInvites);
                        currentPlayers = currentPlayers.filter(player => player !== this.props.currentUser)
                        this.setState({submitted: true, listOfPlayers: currentPlayers, failedInvites: data.failedToSend});
                    }
                    else{
                        this.setState({submitted: false, failedInvites: data.failedToSend});
                    }
                }
            );
        }
    }

    renderSearchResults(){
        if(this.state.playerID === "")
            return <p></p>;
        else if(this.state.searchResults.length === 0){
            return(
                <Table>
                    <TableBody>
                        <TableRow>
                            <TableCell>
                                No players found!
                            </TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            );
        }
        else{
            let results = this.state.searchResults.map(player => {
                return(
                    <TableRow key={player}>
                        <TableCell>
                            {player} 
                        </TableCell>
                        <TableCell>
                            <CheckIcon onClick={this.sendInvite.bind(this, player)}/>
                        </TableCell>
                    </TableRow>
                );
            })
            return(
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Users</TableCell>
                            <TableCell align="left">Send Invite?</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {results}
                    </TableBody>
                </Table>
            );
        }
    }

    renderForms(){
        return(
            <Form>
                <FormGroup>
                    <Label>Send Invitation</Label>
                    <InputGroup>
                        <Input name="player_id" id="enter_player_id" placeholder="Enter player username"
                               onChange={this.changePlayer}/>
                        {this.renderSearchResults()}
                    </InputGroup>
                </FormGroup>
            </Form>
        );
    }

    getCurrentPlayers(){
        let currentPlayers = this.state.listOfPlayers;
        let ret = "";
        currentPlayers.forEach(player => {
            if(currentPlayers[0] !== player)
                ret += ", ";
            ret += player;
        });
        return ret+"\n";
    }

    render() {
        let dontStart = this.state.listOfPlayers.length === 0
        return(
            <div>
                <Button onClick={this.toggle}>Create New Match</Button>
                <Modal isOpen={this.state.showCreateMatch} toggle={this.toggle}>
                    <ModalHeader toggle={this.toggle}>Create Match</ModalHeader>
                    <ModalBody>
                        {this.renderForms()}
                        <p>Sent Invites to: </p>
                        {this.getCurrentPlayers()}
                    </ModalBody>
                    <ModalBody>
                        <Button onClick={this.startMatch} disabled={dontStart}>Create Match</Button>
                    </ModalBody>
                </Modal>
            </div>
        );
    }
}