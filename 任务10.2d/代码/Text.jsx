import React from 'react'
function Text (props)
{
    return <div className={'text-div ' + (props.className||'')} ><p>{props.text}</p></div>
}
export default Text