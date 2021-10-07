import React from "react";
import { Input,Button } from 'semantic-ui-react';
import './Input.css'
  
function MenuItem(props){
  var keyword = '';
  const handleChange=(e)=>{
    const {name, value} = e.target
    keyword = value
  }
  const Submit = ()=>{
    // console.log('Submit',keyword)
    if(props.getData) props.getData(keyword);
  }
    return(<div className='input-div'>
      <Input onChange={handleChange} icon='search' placeholder='Search(title/date/suburb)...' />
      <Button type='submit' onClick={Submit}>Submit</Button>
      </div>
     )
}
export default MenuItem


    