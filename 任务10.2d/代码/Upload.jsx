import React from 'react'
function Upload (props)
{   var file = null;
    const handleChange=e=>{
        file = e.target.files[0];
       
    }
    const Upload = e=>{
        if(!file) {
            alert('please select file');
            return
        }
         const fr = new FileReader();
        fr.readAsDataURL(file);
        let fileContent = null;
        fr.onload = () => {
            fileContent = fr.result;
            var index = fileContent.indexOf(',')
            // console.log(fileContent,fileContent.substr(index+1));
            fetch('http://localhost:5000/upload', {
            method: 'post',
            headers: {'Content-Type': 'application/json'},
            body : JSON.stringify({
                file: fileContent.substr(index+1)
            })
        })
        .then(response => response.json())
        .then(data =>{
            console.log(data);
            props.getImg('http://localhost:5000/'+data.src)
        })
        .catch(err => {
            console.log("Error:" + err)
        })

        };
        // console.log(file);
    }
    return <span  className='upload'>
        <input onChange={handleChange} type="file" accept="image/*"/>
        <button onClick={Upload}>Upload</button>
    </span>
}
export default Upload