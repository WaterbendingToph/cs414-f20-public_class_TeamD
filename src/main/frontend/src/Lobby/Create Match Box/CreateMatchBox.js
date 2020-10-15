import React, {Component} from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';

export default class CreateMatchBox extends Component{
    constructor(props){
        super(props)
        this.state = {
            showCreateMatch: false
        };

        this.toggle = this.toggleCM.bind(this);
    }

    toggleCM(){
        let setCM = !this.state.showCreateMatch;
        this.setState({showCreateMatch: setCM});
    }

    render() {
        return(
            <div>
                <Button onClick={this.toggle}>Create New Match</Button>
                <Modal isOpen={this.state.showCreateMatch} toggle={this.toggle}>
                    <ModalHeader toggle={this.toggle}>Create Match</ModalHeader>
                    <h2>Just a thing</h2>
                </Modal>
            </div>
        );
    }
}