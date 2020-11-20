import React, {Component} from 'react';
import { Modal, ModalBody, ModalHeader, Alert } from 'reactstrap';
import MailOutlineIcon from '@material-ui/icons/MailOutline';
import CheckIcon from '@material-ui/icons/Check';
import NotInterestedIcon from '@material-ui/icons/NotInterested';

export default class InviteBox extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showInvites: false,
            invites: [],
            failAccept: false
        }
    }

    componentDidMount(){
        fetch("/getInvites/?current=" + this.props.current)
            .then(res => res.json())
            .then(data => this.setState({invites: data.invitesFromPlayers}));
    }

    toggle(){
        let current = !this.state.showInvites;
        this.setState({showInvites: current})
    }

    deleteUser(user){
        let newInvites = this.state.invites.filter(player =>{
            return player !== user;
        })
        fetch("/deleteInvite?current="+ this.props.current +"&player=" + user)
            .then(res => res.text())
            .then(data => {
                console.log("Rtrieved from back: " + data)
                if(data !== "false"){
                    this.setState({invites: newInvites})
                }
            });
    }

    acceptInvite(user){
        console.log("Accepting: ", user);
    }

    getInvites(){
        if(this.state.invites.length !== 0){
            let invites = this.state.invites.map(user => {
                if(user !== "")
                    return(<li key={user}>{user} <CheckIcon onClick={this.acceptInvite.bind(this, user)}/> <NotInterestedIcon onClick={this.deleteUser.bind(this, user)}/></li>);
            });
            return(
                <div>
                    <ul>
                        {invites}
                    </ul>
                </div>
            );
        }
    }

    toggleFailAccept(){
        this.setState({ failAccept: !this.state.failAccept })
    }

    render() {
        return(
            <div>
                <MailOutlineIcon onClick={this.toggle.bind(this)} />
                <Modal isOpen={this.state.showInvites} toggle={this.toggle.bind(this)}>
                    <Alert color="danger" isOpen={this.state.failAccept} toggle={this.toggleFailAccept.bind(this)}>Invite has expired</Alert>
                    <ModalHeader>
                        Invitations
                    </ModalHeader>
                    <ModalBody>
                        {this.getInvites()}
                    </ModalBody>
                </Modal>
            </div>
        );
    }
}