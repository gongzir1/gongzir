import React from 'react'
import './Footer.css'
import MenuItem from './MenuItem'
import ContantItem from './ContantItem'
function Footer (props)
{
    return <div className='footer-div' >
    <a href="">NEWSLETTER SIGN IN</a>
    <MenuItem getData={props.getData}/>
    <ContantItem />
    </div>

}
export default Footer