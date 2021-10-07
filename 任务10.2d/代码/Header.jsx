import React from 'react'
import './Header.css'
function Header (props)
{
    return <div className='header-div' ><h1>{props.text}</h1></div>
}
export default Header;