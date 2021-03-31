import React, {Component} from 'react'
import axios from 'axios'
import Card from "semantic-ui-react/dist/commonjs/views/Card";
import {CardContent, CardHeader, Icon} from "semantic-ui-react";
import {withRouter} from 'react-router-dom'
import * as Yup from "yup";
import {Formik, Form, Field} from 'formik'


class ArticleTableForm extends Component {


    handleSubmit(values){

        axios.post('/addToList/' + sessionStorage.getItem("listId"), values)
        this.props.history.push("/items")
    }



    render() {

        return(

            <Formik initialValues={{name:'', amount:''}}
                    validationSchema={Yup.object().shape({name:Yup.string().min(2,'Too short')
                                                                      .required('Required').max(50,'Too long'),
                                                          amount:Yup.string().required('Required')})}
                    onSubmit={(values, {resetForm}) => {
                        this.handleSubmit(values)
                        resetForm({values:''})
                    }}
            >
                {({errors, touched}) => (
                    <Form className='ui form'>
                        <Card>
                            <CardContent>
                                <label style={{color:'black', fontFamily:'Arial'}}>Article name</label>
                                <Field name='name' placeHolder='Article name'></Field>
                                {errors.name && touched.name ? (
                                    <div style={{color:"red", fontFamily:'Arial'}}>{errors.name}</div>
                                ) : null}
                                <label style={{color:'black', fontFamily:'Arial'}}>Amount</label>
                                <Field name='amount' placeHolder='Amount'></Field>
                                {errors.amount && touched.amount ? (
                                    <div style={{color:"red", fontFamily:'Arial'}}>{errors.amount}</div>
                                ) : null}
                            </CardContent>
                            <CardContent extra>
                                <button type="submit" className="ui orange button">
                                    <Icon className='plus'></Icon>
                                    Submit
                                </button>
                            </CardContent>
                        </Card>
                    </Form>
                )}
            </Formik>

        );

    }

}

export default  withRouter(ArticleTableForm)