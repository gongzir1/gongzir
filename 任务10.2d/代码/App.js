import React,{useState}from 'react';
import './App.css';
import PostTask from './PostTask';
import PostTaskOnline from './PostTaskOnline';
import 'semantic-ui-css/semantic.min.css';
import Header from './Header';
//Checkbox
import { Form } from 'semantic-ui-react';
// var type =false;

function App() {
    const [contact, setContact] = useState('person')
    function handleChange(e)
{
  setContact(e.target.value)
  // type={contact}
}
  return(
    <div>
 <Header 
        text = "New task"
      />
      <Form>
    
    <Form.Group grouped className='task-type'>
     
      <label className='mr20'>Select Task Type :</label>
      <Form.Field
        label='In person'
        control='input'
        type='radio'
        name='htmlRadios'
        checked={contact=='person'}
       onChange={handleChange}
       className='mr20'
       value='person'
      />
      <Form.Field
        label='Online'
        control='input'
        type='radio'
        name='htmlRadios'
        checked={contact=='online'}
        onChange={handleChange}
        value='online'
      />
    </Form.Group>
    </Form>
{/* <PostTask /> */}
  
   {
      
     contact === 'person' ?
    <PostTask />

    :
    <PostTaskOnline />
}

    

</div>
  )
}
export default App;
