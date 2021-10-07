import React, { Component } from 'react';
//Checkbox
import { Form } from 'semantic-ui-react'
import 'semantic-ui-css/semantic.min.css' 
class MySelect extends Component {  
 isOnline=false
    state = {}
    handleChange = (e, { value }) => this.setState({ value })
    render() {
      //const isOnline = false
      return (
        <Form>
          <Form.Field >
            Select Task Type : <b>{this.state.value}</b>
            <select
   radio
   label='In person'
   name='checkboxRadioGroup'
   value='InPerson'
   checked={this.state.value === 'inpersion'}
 />

 <select
   radio
   label='Online'
   name='checkboxRadioGroup'
   value='Online'
   checked={this.state.value === 'online'}
   onChange={this.handleChange}
  />
            
          
           
          
            

          </Form.Field>
        </Form>
      )
    }
    }
  export default MySelect