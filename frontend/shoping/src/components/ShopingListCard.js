import React, {Component} from 'react'
import {Card, CardHeader, CardContent, CardDescription, Button, Icon, Label, Transition} from "semantic-ui-react";
import axios from "axios";
import {withRouter} from 'react-router-dom'
import '../css/Card.css'


class ShopingListCard extends Component{


    open(listId) {
        sessionStorage.setItem("listId", listId);
        this.props.history.push("/items")
    }

    async change(listId){
        await axios.post("/draftStatus", listId)
    }

    showDraftStatus(list) {
        if(list.draft){
            return <Button as='div' labelPosition='right' >
                <Button color='gray'>
                    <Icon name='check circle blue'></Icon>
                </Button>
                <Label color='blue' as='a' basic pointing='left'>Published</Label>
            </Button>
        }
        else{
            return <Button as='div' labelPosition='right' >
                <Button color='gray' onClick={() => {this.change(list.id)}}>
                    <Icon name='times white gray'></Icon>
                </Button>
                <Label color='gray' as='a' basic pointing='left'>Draft</Label>
            </Button>
        }
    }


    async deleteList(listId) {
        const message = await axios.delete("/deleteList/" + listId)
    }


    render() {
        return(
            <Card>
                <CardContent>
                    <CardHeader>{this.props.list.listName}</CardHeader>
                    <CardDescription>{this.showDraftStatus(this.props.list)}</CardDescription>
                </CardContent>
                <CardContent extra>
                    <div className='ui two buttons'>
                        <Button className={'ui button'} style={{backgroundColor:'#ABDFF1'}} onClick={this.open.bind(this,this.props.list.id)}>OPEN</Button>
                        <button className={'ui button'} onClick={this.deleteList.bind(this, this.props.list.id)}>DELETE</button>

                    </div>

                </CardContent>
            </Card>
        )
    }

}

export default withRouter(ShopingListCard)