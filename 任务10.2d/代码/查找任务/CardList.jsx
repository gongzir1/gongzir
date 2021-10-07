import React, { useState } from 'react'
import Card from './Card'
// import faker from 'faker'
// import expertList from './expertList'
import './Card.css'

/*function cardComponent (expert , i){
    return <Card 
    key = {expert.key}
    avatar = {expert.avatar}
    name = {expert.name}
    position = {expert.position}
    description={expert.description}
  />
}*/


const CardList = (props) => {
 

  return <div className="row">
    {props.expertList.map((expert) =>
      <Card
        key={expert.key}
        avatar={expert.avatar}
        name={expert.title}
        description={expert.description}
        suburb={expert.suburb}
        date={expert.date}
        id={expert._id}
        taskType={expert.taskType}
        budget={expert.budget}
        budgetType={expert.budgetType}
        getData={props.getData}
      />
    )}

  </div>
}

export default CardList