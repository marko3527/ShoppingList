import React from "react";
import {Spring} from "react-spring/renderprops";

export default function WelcomeComponent() {
    return(
        <Spring from={{opacity:0, marginTop: -500, }}
                to={{opacity:1, marginTop: 0}}
        >
            { props => (
                <div style={props}>
                    <div style={welcomeStyle}>
                        <h1 style={{fontFamily:'Arial', fontSize:'80px'}}>SHOPPING LIST MANAGER</h1>
                        <p style={{fontFamily:'Arial', fontSize:'30px', color:'orange', padding:'150px'}}>
                            Welcome to shopping list manager. It helps you dealing with everyday struggle of
                            remembering what items to buy in store. You can navigate by clicking on items in navbar
                        </p>
                    </div>
                </div>
            )}

        </Spring>

    )
}

const welcomeStyle = {
    color:'#F4F3EA'
}