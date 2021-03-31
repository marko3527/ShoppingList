import React, {Component} from 'react';
import {Menu,Image} from 'semantic-ui-react'
import '../css/Navbar.css'
import {withRouter} from 'react-router-dom'

class Navbar extends Component {

    constructor(props) {
        super(props);
    }

    renderLists = () => {
        this.props.history.push("/lists")
    }

    renderDrafts = () => {
        this.props.history.push("/drafts")
    }

    renderPublished = () => {
        this.props.history.push("/published")
    }

    renderHome = () => {
        this.props.history.push("/")
    }



    render() {

        return(
            <div className='navbar' >
                <Menu style={{backgroundColor:'#F4F3EA'}}>
                    <Menu.Item name='Home'  onClick={this.renderHome.bind(this)}></Menu.Item>
                    <Menu.Item name='Lists' onClick={this.renderLists.bind(this)}></Menu.Item>
                    <Menu.Item name='Drafts' onClick={this.renderDrafts.bind(this)}></Menu.Item>
                    <Menu.Item name='Published' onClick={this.renderPublished.bind(this)}></Menu.Item>
                </Menu>
            </div>
        )

    }

}

export default withRouter(Navbar)