import React, {Component} from "react";
import axios from "axios";
import '../css/Card.css';
import ShopingListCard from "./ShopingListCard";
import AddShopListFormCard from "./AddShopListFormCard";
import ReactCSSTransitionGroup from 'react-addons-css-transition-group'
import '../css/Animation.css'

class AllLists extends Component {

    state = {
        lists: []
    }

    async componentDidUpdate(prevProps) {
        const response = await axios.get('/showLists' )
        const body = await response.data;
        this.setState({lists: body});
    }

    async componentDidMount() {
        const response = await axios.get('/showLists' )
        const body = await response.data;
        this.setState({lists: body});
    }


    render() {
        const {lists} = this.state;
        return(
            <div className="ui four column divided grid" style={{textAllign:'center'}}>
                <div className="ui cards" style={{margin:'auto'}}>
                    <div className="row">
                        <div className="column">
                            <ReactCSSTransitionGroup
                                transitionName="fade"
                                transitionEnterTimeout={1000}
                                transitionLeaveTimeout={1000}
                                transitionAppear={true}
                                transitionAppearTimeout={1000}
                            >
                                {lists.map(list =>
                                    <div>
                                        <ShopingListCard key={list.listId} list={list}></ShopingListCard>
                                    </div>
                                )}
                            </ReactCSSTransitionGroup>
                            <AddShopListFormCard></AddShopListFormCard>
                        </div>

                    </div>
                </div>

            </div>
        );
    }
}

export default AllLists;