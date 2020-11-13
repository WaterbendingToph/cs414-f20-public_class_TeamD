import React, {Component} from 'react';
import Square from "./Square";
// import { Button, Modal, ModalHeader, ModalBody, InputGroupAddon,
//         Form, FormGroup, Label, Input, InputGroup } from 'reactstrap';

export default class BoardGame extends Component{



    render(){
        return (
            <div className="App">
              <h1>Currently the default chessboard setup!</h1>
                <tbody>
                    <Square></Square>
                </tbody>
            </div>
        );
    }
}
