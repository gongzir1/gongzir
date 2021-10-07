import React,{useState,useEffect} from 'react';
import './App.css';
import Header from './Header'
import Footer from './Footer';
import CardList from './CardList'
import Image from './Image';
import Form from './Form';


function App() {

  const [expertList, setexpertList] = useState([]);
  const getData = (keyword='')=>{
    // console.log('keyword=',keyword);
     fetch('http://localhost:5000/?keyword='+keyword, {
      method: 'get',

    })
      .then(response => response.json())
      .then(data => {
        console.log(data)
        setexpertList(data.map((item, i) => {
          item.key = i;
          return item;
        }))
      })
      .catch(err => {
        console.log("Error:" + err)
      })
  }
  useEffect(function(){
    getData()
  },[]);
  return (
    <div>
    <Header />
    <Image />
   <CardList  expertList={expertList}/>
   <Footer getData={getData} />
   {/* <Form /> */}
    </div>
  );
}

export default App;
