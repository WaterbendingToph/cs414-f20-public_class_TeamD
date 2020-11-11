import React, {Component} from 'react';
import { Modal, ModalBody, ModalHeader } from 'reactstrap';
import MailOutlineIcon from '@material-ui/icons/MailOutline';
import CheckIcon from '@material-ui/icons/Check';
import NotInterestedIcon from '@material-ui/icons/NotInterested';

export default class InviteBox extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showInvites: false,
            invites: []
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

    getInvites(){
        let invites = this.state.invites.map(user => {
            return(<li>{user} <CheckIcon /> <NotInterestedIcon /></li>);
        });
        return(
            <div>
                <ul>
                    {invites}
                </ul>
            </div>
        );
    }

    render() {
        return(
            <div>
                <MailOutlineIcon onClick={this.toggle.bind(this)} />
                <Modal isOpen={this.state.showInvites} toggle={this.toggle.bind(this)}>
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