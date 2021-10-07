import React,{useState}from 'react'

const InputMoney = (props)=>{
    const [contact, setContact] = useState({
        budget:'',
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
    return <div>
       <input name='budget'
        type='number'
         placeholder='$'
          onChange ={handleChange}
          value = {contact.budget}
          />
    </div>

}
export default InputMoney