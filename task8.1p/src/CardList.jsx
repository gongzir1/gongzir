import React from 'react'
import Card from './Card'
import faker from 'faker'
import expertList from './expertList'
import './Card.css'

function cardComponent (expert , i){
    return <Card 
    key = {expert.key}
    avatar = {expert.avatar}
    name = {expert.name}
    position = {expert.position}
    description={expert.description}
  />
}


const CardList = () =>
{  
    return <div className="row">
    {expertList.map(  (expert ) => 
      <Card 
    key = {expert.key}
    avatar = {expert.avatar}
    name = {expert.name}
   // description = {expert.description}
    position={expert.position}
  />
)}
     
    </div>
}

export default CardList