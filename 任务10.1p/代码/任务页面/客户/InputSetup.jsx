import React,{useState} from 'react'
import './label.css'
import Button1 from './Button'

const InputSetup = (props)=>{
    
    const [contact, setContact] = useState({
        suburb: '',
        date: ''
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
         <label htmlFor="suburb" className="form-label">Suburb:  </label>
       <input name= 'suburb'
       type= 'text'
       placeholder ='Enter a suburb'
       onChange = {handleChange}
       value = {contact.suburb} />
       <br/><br/>
       <label htmlFor="date" className="form-label">Date:</label>
        <input name= 'date'
       type= 'text'
       placeholder ='Enter a date'
       onChange = {handleChange}
       value = {contact.date} />
    
    </div>

}
export default InputSetup