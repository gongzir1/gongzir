import React,{useState} from 'react'
import { Form,Checkbox } from 'semantic-ui-react'

const Testselect = (props) => {
  handleChange = (e, { value }) => this.setState({ value })
  const [contact, setContact] = useState('')
// const handleChange = (event)=>{
//     const {name, value} = event.target
//     setContact ((preValue)=>{  
//     return {
//     ...preValue,
//     [name]: value
//     }
//     })
// }
// const [searchTerm , setsearchTerm] = useState('')
  
function handleChange(e)
{
  setContact(e.target.value)
}
  return <div>
  <Form>
    
    <Form.Group grouped>
      <label>Select Task Type :</label>
      <Form.Field
        label='In person'
        control='input'
        type='radio'
        name='htmlRadios'
       onChange={handleChange}
      />
      <Checkbox
            radio
            label='Online'
            name='checkboxRadioGroup'
            value={contact}
           // checked={this.state.value === 'person'}
            onChange={handleChange}
          />
           <Checkbox
            radio
            label=' I n person'
            name='checkboxRadioGroup'
            value={contact}
           // checked={this.state.value === 'person'}
            onChange={handleChange}
          />
      <Form.Field
        label='Online'
        control='input'
        type='radio'
        name='htmlRadios'
        onChange={handleChange}
      />
    </Form.Group>
    <Form.Field label='An HTML <textarea>' control='textarea' rows='3' />
  </Form>
)
</div>
}
export default Testselect
