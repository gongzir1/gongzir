import React from "react";
import { Input,Button } from 'semantic-ui-react';
import './Input.css'
  
function MenuItem(){
    return(<div className='input-div'>
      <Input icon='search' placeholder='Search...' />
      <Button type='submit'>Submit</Button>
      </div>
     )
}
export default MenuItem


    