import * as React from "react";
import Card from "semantic-ui-react/dist/commonjs/views/Card";
import {CardContent, CardHeader, FormField, Icon} from "semantic-ui-react";
import axios from "axios";
import {withRouter} from 'react-router-dom'
import * as Yup from 'yup';
import {Formik, Form, Field} from "formik";

class AddShopListFormCard extends React.Component {


    async addList(values){
        const response = await axios.post('/newList' , values)
        sessionStorage.setItem("listId", response.data.id)
        this.props.history.push("/items")

    }

    render() {
        return (
            <Formik initialValues={{listName:''}}
                    validationSchema={Yup.object().shape({listName:Yup.string().min(2,'Too short')
                                                                      .required('Required').max(50,'Too long')})}
                    onSubmit={(values, {resetForm}) => {
                        this.addList(values)
                        resetForm({values:''})
                    }}>
                {({ errors, touched }) => (
                    <Form className="ui form">
                        <Card>
                            <CardContent>
                                <CardHeader>Add new list</CardHeader>
                                <Field name='listName' placeHolder='List name'></Field>
                                {errors.listName && touched.listName ? (
                                    <div style={{color:"red", fontFamily:'Arial'}}>{errors.listName}</div>
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

export default withRouter(AddShopListFormCard)