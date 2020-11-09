import React, {Component} from 'react';
import { Modal } from 'reactstrap';
import MailOutlineIcon from '@material-ui/icons/MailOutline';

export default class InviteBox extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showInvites: false
        }
    }

    toggle(){
        let current = !this.state.showInvites;
        this.setState({showInvites: current})
    }

    render() {
        return(
            <div>
                <MailOutlineIcon onClick={this.toggle.bind(this)} />
                <Modal isOpen={this.state.showInvites} toggle={this.toggle.bind(this)}>
                    <h1>Am a lonely boi</h1>
                </Modal>
            </div>
        );
    }
}