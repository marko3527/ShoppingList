import React, {Component} from 'react';
import axios from "axios";
import {
    Button,
    Label,
    Icon,
    Table,
    TableHeader,
    TableHeaderCell,
    TableRow,
    TableBody,
    TableCell,
    ListContent,
    List
} from "semantic-ui-react";
import ArticleTableForm from "./ArticleTableForm";
import ReactCSSTransitionGroup from 'react-addons-css-transition-group'



class ArticleList extends Component {

    state = {
        isLoading: true,
        list: [],
    }


    async componentDidUpdate(prevProps) {
        const response = await axios.get('/listItems/' + sessionStorage.getItem("listId"))
        const body = await response.data;
        this.setState({isLoading: false, list: body});
    }

    async componentDidMount() {
        const response = await axios.get('/listItems/' + sessionStorage.getItem("listId"))
        const body = await response.data;
        this.setState({isLoading: false, list: body});
    }



    showCartStatus(list, article) {
        if(list.draft){
            if(article.addedToCart){
                return <Button as='div' labelPosition='right' >
                    <Button color='green' onClick={() => {this.change(article.id)}}>
                        <Icon name='check circle white'></Icon>
                    </Button>
                    <Label color='green' as='a' basic pointing='left'>In cart</Label>
                </Button>
            }
            else{
                return <Button as='div' labelPosition='right' >
                    <Button color='red' onClick={() => {this.change(article.id)}}>
                        <Icon name='times white circle'></Icon>
                    </Button>
                    <Label color='red' as='a' basic pointing='left'>Not added</Label>
                </Button>
            }
        }
        return <Button as='div' labelPosition='right' >
            <Button color='red' >
                <Icon name='times white circle'></Icon>
            </Button>
            <Label color='red' as='a' basic pointing='left'>Can't change cart status</Label>
        </Button>

    }

    async remove(articleId) {
        await axios.post("/removeArticle", articleId)
    }

    async change(articleId) {
        await axios.post("/changeStatus", articleId)
    }

    enableAddingArticle(list) {
        if(!list.draft){
            return <ArticleTableForm></ArticleTableForm>
        }
    }

    enableRemovingItem(list, article) {
        if(!list.draft){
             return <TableCell><Button name='white' icon onClick={() => {this.remove(article.id)}}>
                <Icon name='remove large red'></Icon>
            </Button></TableCell>
        }
    }

    async changeDraft(listId){
        console.log(listId)
        await axios.post("/draftStatus", listId)
    }

    showDraftStatus(list) {
        if (list.draft) {
            return(
                <Button as='div' labelPosition='right'>
                    <Button color='gray' >
                        <Icon name='check blue green'></Icon>
                    </Button>
                    <Label color='blue' as='a' basic pointing='left'>Published</Label>
                </Button>
            )
        } else {
            return <Button as='div' labelPosition='right'>
                <Button color='gray' onClick={() => {
                    this.changeDraft(list.id)
                }}>
                    <Icon name='times gray circle'></Icon>
                </Button>
                <Label color='gray' as='a' basic pointing='left'>Draft</Label>
            </Button>
        }
    }


    render() {
        const {isLoading, list} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        return (
            <div style={{textAllign:'center'}}>
                <h style={{fontFamily:'Arial', fontSize:'50px', color:'white'}}>{list.listName}
                    <br/>
                    <div>
                        {this.showDraftStatus(list)}
                    </div>
                </h>
                <br/>
                <Table textAlign='center' verticalAlign='center' style={{backgroundColor:'#F4F3EA',width:'70%', margin:'auto'}}>
                    <TableHeader>
                        <TableRow>
                            <TableHeaderCell>Article</TableHeaderCell>
                            <TableHeaderCell>Cart</TableHeaderCell>
                            <TableHeaderCell>Amount to buy</TableHeaderCell>
                            <TableHeaderCell></TableHeaderCell>
                        </TableRow>
                    </TableHeader>
                    <TableBody>
                        <ReactCSSTransitionGroup
                            transitionName="addItem"
                            transitionEnterTimeout={500}
                            transitionLeaveTimeout={500}
                            transitionAppear={true}
                            transitionAppearTimeout={500}
                        >
                            {list.articles.map(article =>
                                <TableRow>
                                    <TableCell>{article.name}</TableCell>
                                    <TableCell>{this.showCartStatus(list, article)}</TableCell>
                                    <TableCell>{article.amount}</TableCell>
                                    {this.enableRemovingItem(list, article)}
                                </TableRow>
                            )}
                        </ReactCSSTransitionGroup>
                    </TableBody>
                </Table>
                <br/>
                {this.enableAddingArticle(list)}
            </div>
        );
    }

}

export default  ArticleList;