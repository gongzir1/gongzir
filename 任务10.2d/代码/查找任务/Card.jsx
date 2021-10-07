import React, { useState } from 'react'
import './Card.css'

const Card = (props) => {
    const [showMore, setShowMore] = useState(false);
    const setShowMore2 = ()=>{
        setShowMore(!showMore);
    }
    const handleClick = (id) => {

        return () => {
            // console.log(id)
            fetch('http://localhost:5000/delete?id=' + id, {
                method: 'get',

            })
            .then(response => response.json())
                .then(data => {
                    // console.log(data,123321);
                    alert(data.msg);
                    props.getData();
                })
                .catch(err => {
                    console.log("Error:" + err)
                })

        }
    }
    return <div className='column card'>
        <img width="80%" height="100px" src={props.avatar} alt="avatar" />
        <h3>{props.title}</h3>
        <p>{props.description}</p>
        <p>{props.suburb}</p>
        <p>{props.date}</p>
        {showMore? <div>
            <p>Task Type : {props.taskType=='person' ? 'In Person' : props.taskType}</p>
            <p>budget: {props.budget} ({props.budgetType})</p>
        </div> : ''}
       
        <button onClick={handleClick(props.id)} >delete</button>
        <button onClick={setShowMore2} > {showMore?'cancel more' : 'more>'}</button>
    </div>
}

export default Card