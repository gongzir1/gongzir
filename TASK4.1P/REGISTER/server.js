const express = require("express")
const app = express()

app.use(express.static(__dirname+'/'))
//引入模型对象，进行CRUD
const userModel=require('./mongdb/model/user')
//引入数据库模块
const db=require('./mongdb/model/db')
//使用内置中间件用于解析post请求的urlencoded参数
app.use(express.urlencoded({extended:true}))

db(()=>{
    app.get('/register',(req,res)=>{
        res.sendFile(__dirname+"/register.html")
       })
    app.post('/register',(req,res)=>{
        //获取用户输入
        console.log("it is ok")
      //  res.send("okkk")
      console.log(req.body)
        const{country,firstname,lastname,email,password,re_password,address,city,state,zip,phone}=req.body
        //校验数据合法性：
        const passwordReg=/^[a-zA-Z0-9_@]{8,20}$/
        if(country==''){
            res.send('Please select your country')
        }else if(firstname==''){
            res.send('Please input your first name')
        }else if(lastname==''){
            res.send('Please input your last name')
        }
        else if(!passwordReg.test(password)){
            res.send('password must greater than 8 bits')
        }else if(password!=re_password){
            res.send('Confirm password must be same as password')
        }else if(address==''){
            res.send('Please input your address')
        }else if(city==''){
            res.send('Please input your city')
        }else if(state==''){
            res.send('Please input your state')
        }else if(zip==''){
            res.send('Please input your ZIP')
        }
        else{
            userModel.findOne({email},function(err,data){
                if(data){
                    res.send('This email has been registered, please try another email')
                }else{
                    userModel.create({country,firstname,lastname,email,password,address,city,state,zip,phone},function(err,data){
                        if(!err){
                            res.send('successfully!')


var send = require('./email.js');
var sendmail='<div> <a href="https://mailchi.mp/102ae4f6309b/welcome?e=[UNIQID]">You have registered iServer successfully! click to know more!</a><div>'
// 创建一个邮件对象
var mail = {
    // 发件人
    from: 'g13546576777@163.com>',
    // 主题
    subject: 'Welcome to iServer',
    // 收件人
    to: email,
    // 邮件内容，HTML格式
    html:sendmail
    
};
send(mail);

                        }
                            else{
                                console.log(err)
                                res.send('sorry,something wrong...please try again')
                            }

                      })
                   }
                
            })
    
        }
        //  校验成功：去数据库查该邮箱是否注册
        //  校验失败：提示用户
    
    })
    // app.post('/register',(req,res)=>{
    //     console.log("I am posting now")
    // })
    app.listen(3000,function(request,response){
      console.log("Server is running on port 3000")
    })
},
    (err)=>{
        console.log(err111)
    })

