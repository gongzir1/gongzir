import React,{useState} from 'react'
import Button from './Button'
import Text from './Text';
import Header from './Header';
import './Button.css'

const PostTaskOnline = (props)=>{
 const [contact, setContact] = useState({
            title: '',
            description: '',
            date:'',
            budget:'',
 })
    
    
    const handleSubmit = ()=>{
        fetch('http://localhost:5000/task', {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            body : JSON.stringify({
                title : contact.title,
                description: contact.description,
                date: contact.date,
                budget:contact.budget
            })
        })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(err => {
            console.log("Error:" + err)
        })
    }
    const handleChange = (event)=>{
        const {name, value} = event.target
        setContact ((preValue)=>{  
        return {
        ...preValue,
        [name]: value
        }
        })
    }
    return <div>
    <Header 
      text = "New Task"
    />
   
  <br></br>
   {/* input description */}
  <Header 
      text = "Desribe your task to Experts"
    />
        <label htmlFor="title" className="form-label"> Task   Title:  </label>
       <input name= 'title'
       type= 'text'
       placeholder ='Enter task title'
       onChange = {handleChange}
       value = {contact.title} 
       />
       <br/><br/>
       <label htmlFor="description" className="form-label">Description:</label>
        <input name= 'description'
       type= 'text'
       placeholder ='Enter task description'
       onChange = {handleChange}
       value = {contact.description} 
       />
 <br></br>
 {/* inputsetup */}
  <Header 
      text = "Setting up your Task"
    />
  <Text
  text="This section is designed based on the type of the task. It could be developed by conditional rendering. For an in-person task, both suburb and date
  could be developed ."
  />
  <Text
  text="For an online task, only date would be appeared."
  />
  <br/><br/>
  {/* <label htmlFor="suburb" className="form-label">Suburb:  </label>
       <input name= 'suburb'
       type= 'text'
       placeholder ='Enter a suburb'
       onChange = {handleChange}
       value = {contact.suburb} /> */}
       <br/><br/>
       <label htmlFor="date" className="form-label">Date:</label>
        <input name= 'date'
       type= 'text'
       placeholder ='Enter a date'
       onChange = {handleChange}
       value = {contact.date} />
       {/* input budget */}
       <Header 
      text = "Suggest how much"
    />
    <Text
    text="What is your budget?
    (This is an estimate)"
    />
     <input name='budget'
        type='number'
         placeholder='$'
          onChange ={handleChange}
          value = {contact.budget}
          />
          <br/><br/>
    <Button 
        type = 'submit'
           text='Post a new task'
           onClick= {handleChange,handleSubmit}

           />
    </div>
}

export default PostTaskOnline