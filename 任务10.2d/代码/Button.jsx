import React from 'react'
const Button = (props) =>{
    return <div className='button-div'><button type = {props.type} onClick={props.onClick} > {props.text} </button></div>
}
export default Button