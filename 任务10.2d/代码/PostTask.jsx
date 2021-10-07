import React, { useState } from 'react'
import Button from './Button'
import Text from './Text';
import Header from './Header';
import './Button.css'
import { Form } from 'semantic-ui-react'
import Upload from './Upload'
// import Testselect from './Testselect';
// import MySelect from './MySelect';

const PostTask = (props) => {
    const [contact, setContact] = useState({
        title: '',
        description: '',
        suburb: '',
        date: '',
        budget: '',
        taskType: 'person',
        budgetType: 'Total',
        avatar: ''
    })

    const handleSubmit = () => {
        fetch('http://localhost:5000/task', {
            method: 'post',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                title: contact.title,
                description: contact.description,
                suburb: contact.suburb,
                date: contact.date,
                budget: contact.budget,
                taskType: contact.taskType,
                budgetType: contact.budgetType,
                avatar: contact.avatar
            })
        })
            .then(response => response.json())
             .then(data => {
                alert('new task success')
                console.log(data)
            })
            .catch(err => {
                alert('new task fail')
                console.log("Error:" + err)
            })
    }
    const handleChange = (event) => {
        const { name, value } = event.target
        setContact((preValue) => {
            return {
                ...preValue,
                [name]: value
            }
        })
    }
    const getImg = (src) => {
        // console.log('getImg', src);
        setContact((preValue) => {
            return {
                ...preValue,
                avatar: src
            }
        })
    }
    return <div>
        {/* <Header 
      text = "New Task"
    /> */}
        {/* select */}
        {/* <Form>
    
    <Form.Group grouped>
      <label>Select Task Type :</label>
      <Form.Field
        label='In person'
        control='input'
        type='radio'
        name='htmlRadios'
       onchange={handleChange}
      />
      <Form.Field
        label='Online'
        control='input'
        type='radio'
        name='htmlRadios'
      />
    </Form.Group>
    </Form> */}
        {/* <Testselect /> */}
        <br></br>
        {/* input description */}
        <Header
            text="Desribe your task to Experts"
        />
        <label htmlFor="title" className="form-label"> Task   Title:  </label>
        <input name='title'
            type='text'
            placeholder='Enter task title'
            onChange={handleChange}
            value={contact.title}
        />
        <br /><br />
        <label htmlFor="description" className="form-label">Description:</label>
        <input name='description'
            type='text'
            placeholder='Enter task description'
            onChange={handleChange}
            value={contact.description}
        />
        <br />
        <label htmlFor="description" className="form-label">add your image:</label>
         {contact.avatar? <img src={contact.avatar} width='50px'/>:''}
        <Upload getImg={getImg}/>
        
        <br></br>
        {/* inputsetup */}
        <Header
            text="Setting up your Task"
        />
        <Text
            className="center"
            text="This section is designed based on the type of the task. It could be developed by conditional rendering. For an in-person task, both suburb and date
            could be developed ."
  />
  <Text
            className="center"
            text="For an online task, only date would be appeared."
        />
        <br /><br />
        <label htmlFor="suburb" className="form-label">Suburb:  </label>
        <input name='suburb'
            type='text'
            placeholder='Enter a suburb'
            onChange={handleChange}
            value={contact.suburb} />
        <br /><br />
        <label htmlFor="date" className="form-label">Date:</label>
        <input name='date'
            type='text'
            placeholder='Enter a date'
            onChange={handleChange}
            value={contact.date} />
        {/* input budget */}
        <Header
            text="Suggest how much"
        />
        <Text
            text="What is your budget?" />
        <Text
            text="(This is an estimate)" />
        <div className="flex">
            <Form.Field
                label='Total'
                control='input'
                type='radio'
                name='budgetType'

                onChange={handleChange}
                className='mr20'
                checked={contact.budgetType == 'Total'}
                value='Total'
            />
            <Form.Field
                label='Hourly rate'
                control='input'
                type='radio'
                name='budgetType'
                onChange={handleChange}
                checked={contact.budgetType == 'Hourly rate'}
                value='Hourly rate'
            />
        </div>
        <input name='budget'
            type='number'
            placeholder='$'
            onChange={handleChange}
            value={contact.budget}
        />
        <br /><br />
        <Button
            type='submit'
            text='Post a new task'
            onClick={handleSubmit}

        />
    </div>
}

export default PostTask