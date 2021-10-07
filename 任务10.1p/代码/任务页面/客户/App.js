import React,{useState}from 'react';
import './App.css';
import PostTask from './PostTask';
import PostTaskOnline from './PostTaskOnline';
import 'semantic-ui-css/semantic.min.css';
import Header from './Header';
import { Form,Checkbox } from 'semantic-ui-react';
const type =false;


function App() {
    const [contact, setContact] = useState('')
    function handleChange(e)
{
  setContact(e.target.value)
  type={contact}
}
  return(
    <div>
 <Header 
        text = "New task"
      />
      <Form>
    
    <Form.Group grouped>
      <label>Select Task Type :</label>
      <Form.Field
        label='In person'
        control='input'
        type='radio'
        name='htmlRadios'
       onChange={handleChange}
       value='person'
      />
      <Form.Field
        label='Online'
        control='input'
        type='radio'
        name='htmlRadios'
        onChange={handleChange}
        value='online'
      />
    </Form.Group>
    </Form>
{/* <PostTask /> */}
      
    
  
  
  
   {
      
     type === false ?
    <PostTask />

    :
    <PostTaskOnline />
}

    

</div>
  )
}
export default App;
