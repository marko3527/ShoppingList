import React from 'react';
import './css/App.css'
import {Route, Router, Switch} from "react-router-dom";
import ArticleList from "./components/ArticleList";
import AllLists from "./components/AllLists";
import Navbar from "./components/Navbar";
import Drafts from "./components/Drafts";
import Published from "./components/Published";
import StartingPage from "./components/StartingPage";

export default function App() {


    return(
        <div>
            <Navbar></Navbar>
            <main>
                <Switch>
                    <Route exact={true} path='/' component={StartingPage}></Route>
                    <Route exact={true} path='/lists' component={AllLists}></Route>
                    <Route exact={true} path='/published' component={Published}></Route>
                    <Route exact={true} path='/drafts' component={Drafts}></Route>
                    <Route exact={true} path='/items' component={ArticleList}></Route>

                </Switch>
            </main>
        </div>


    );
}