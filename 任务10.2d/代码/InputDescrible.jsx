import React,{useState} from 'react'
import './label.css'
import Button from './Button'

const InputDescrible = (props)=>{
    const [contact, setContact] = useState({
        title: '',
        description: ''
    })
   
    const handleSubmit = ()=>{
        fetch('http://localhost:5000/task', {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            body : JSON.stringify({
                title : contact.title,
                description: contact.description,
                suburb : contact.suburb,
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
         <label htmlFor="title" className="form-label"> Task   Title:  </label>
       <input name= 'title'
       type= 'text'
       placeholder ='Enter task title'
       onChange = {handleChange}
       value = {contact.title} />
       <br/><br/>
       <label htmlFor="description" className="form-label">Description:</label>
        <input name= 'description'
       type= 'text'
       placeholder ='Enter task description'
       onChange = {handleChange}
       value = {contact.description} />
       <Button 
        type = 'submit'
           text='Post a new task'
           onClick= {handleChange,handleSubmit}

           />
    </div>

}
export default InputDescrible