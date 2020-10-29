import React, {Component} from 'react';
import { Button, Modal, ModalHeader, ModalBody, InputGroupAddon,
        Form, FormGroup, Label, Input, InputGroup } from 'reactstrap';

export default class CreateMatchBox extends Component{
    constructor(props){
        super(props)
        this.state = {
            showCreateMatch: false,
            playerID: "",
            submitted: false,
            listOfPlayers: []
        };

        this.toggle = this.toggleCM.bind(this);
        this.changePlayer = this.changePlayer.bind(this);
        this.submitPlayer = this.submitPlayer.bind(this);
        this.startMatch = this.startMatch.bind(this);
    }

    submitPlayer(){
        if(this.state.playerID.length !== 0){
            let currentPlayers = this.state.listOfPlayers;
            currentPlayers.push(this.state.playerID);
            this.setState({submitted: true, listOfPlayers: currentPlayers});
        }
    }

    changePlayer(event){
        this.setState({playerID: event.target.value});
    }

    toggleCM(){
        let setCM = !this.state.showCreateMatch;
        this.setState({showCreateMatch: setCM});
    }

    didSendInvite(){
        if(this.state.submitted === true)
            return(<p style={{color: "rgb(0,200,0)"}}>Sent Invite!</p>);
        return "";
    }

    startMatch(){
        fetch("/createMatch/?playerID="+ this.state.listOfPlayers.join(","))
            .then(res => res.json())
            .then(res => console.log(res));
    }

    renderForms(){
        return(
            <Form>
                <FormGroup>
                    <Label>Send Invitation{this.didSendInvite()}</Label>
                    <InputGroup>
                        <Input name="player_id" id="enter_player_id" placeholder="Enter player username"
                               onChange={this.changePlayer}/>
                        <InputGroupAddon addonType="append"><Button onClick={this.submitPlayer}>Send</Button></InputGroupAddon>
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
        return(
            <div>
                <Button onClick={this.toggle}>Create New Match</Button>
                <Modal isOpen={this.state.showCreateMatch} toggle={this.toggle}>
                    <ModalHeader toggle={this.toggle}>Create Match</ModalHeader>
                    <ModalBody>
                        {this.renderForms()}
                        <p>Current Players: </p>
                        {this.getCurrentPlayers()}
                    </ModalBody>
                    <ModalBody>
                        <Button onClick={this.startMatch}>Create Match</Button>
                    </ModalBody>
                </Modal>
            </div>
        );
    }
}