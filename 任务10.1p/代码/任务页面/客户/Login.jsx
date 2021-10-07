import React,{useState} from 'react'
import Input from './Input'
import Button from './Button'
import './App.css'


const Login = (props)=>{
        const [contact, setContact] = useState({
            username: '',
            password: ''
        })
       
    

    
    const handleChange = (event)=>{
        const {name, value} = event.target
        setContact ((preValue)=>{  
        return {
        ...preValue,
        [name]: value
        }
        })
    }


    const handleLogin = ()=>{
        fetch('http://localhost:5000/login', {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            body : JSON.stringify({
                username : contact.username,
                password: contact.password
            })
        })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(err => {
            console.log("Error:" + err)
        })
    }
    

    const handleRegister = ()=>{
        fetch('http://localhost:5000/register', {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            body : JSON.stringify({
                username : contact.username,
                password: contact.password
            })
        })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(err => {
            console.log("Error:" + err)
        })
    }


    return <div className= 'header-div'>

      
      
       <Input 
       name= 'username'
       type= 'text'
       placeholder ='username'
       onChange = {handleChange}
       value = {contact.username}
       />

       <br></br>

       <Input 
       name='password'
       type= 'password'
       placeholder ='password'
       onChange = {handleChange}
       value = {contact.password}
       />

       <br></br>

       <Button 
           type = 'submit'
           text='Login'
           onClick= {handleLogin}
       />
       <br></br>
       <Button 
           type = 'submit'
           text='Register'
           onClick= {handleRegister}
       />

    </div>

}
export default Login