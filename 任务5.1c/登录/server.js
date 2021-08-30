const express = require("express")
const app = express()
const https=require("https")
const bcrypt =require('bcrypt')


app.use(express.static(__dirname+'/'))
//引入模型对象，进行CRUD
const userModel=require('./mongodb/model/user')
//引入数据库模块
const db=require('./mongodb/model/db')
const { json, response } = require("express")
//使用内置中间件用于解析post请求的urlencoded参数
app.use(express.urlencoded({extended:true}))

db(()=>{
    app.get('/register',(req,res)=>{
        res.sendFile(__dirname+"/public/register.html")
       })
    app.post('/register',async (req,res)=>{
        //获取用户输入
        // console.log("it is ok")
      //  res.send("okkk")
      const salt=await bcrypt.genSalt(10)
       const hashedPassword=await bcrypt.hash(req.body.password,salt)
        // console.log(hashedPassword)
      console.log(req.body)
        const{country,firstname,lastname,email,password,re_password,address,city,state,zip,phone}=req.body
        const data={
            members:[{
                email_address:email,
                status:"subscribed",
                merge_fields:{
                    FNAME:firstname,
                    LNAME:lastname
                }
            }]
        }
        jsonData=JSON.stringify(data)
        // const apiKey="6838db82fdee020273afb2813258257b-us5"
        // const list_id="5e9b7302c6"
        const url="https://us5.api.mailchimp.com/3.0/lists/5e9b7302c6"
        const options={
            method:"POST",
            auth:"azi:6838db82fdee020273afb2813258257b-us5"
        }
       const request= https.request(url,options,(response)=>{
            response.on("data",(data)=>{
                console.log(JSON.parse(data))
            })
        })
        request.write(jsonData)
        request.end()
        console.log(firstname,lastname,email)
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
                    userModel.create({country,firstname,lastname,email,hashedPassword,address,city,state,zip,phone},function(err,data){
                        if(!err){
                            //  res.send('successfully!')
                            res.redirect('/login')


var send = require('./mail.js');
// var sendmail='<div> <a href="https://mailchi.mp/102ae4f6309b/welcome?e=[UNIQID]">You have registered iServer successfully! click to know more!</a><div>'
// // 创建一个邮件对象
// var mail = {
//     // 发件人
//     from: 'g13546576777@163.com',
//     // 主题
//     subject: 'Welcome to iServer',
//     // 收件人
//     to: email,
//     // 邮件内容，HTML格式
//     html:sendmail
    
// };
// send(mail);
// const message = {
//     from_email: "g13546576777@163.com",
//     subject: "welcome!",
//     text: "Now you can have access to iServer",
//     to: [
//       {
//         email: email,
//         type: "to"
//       }
//     ]
//   };
//   send(message)

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
    app.get('/login',(req,res)=>{
        res.sendFile(__dirname+"/public/login.html")
       })
    //登录请求
    app.post('/login',async(req,res)=>{
        //正则
        const passwordReg=/^[a-zA-Z0-9_@]{8,20}$/
        //校验数据
        const user=await userModel.findOne({
            email:req.body.email
        })
        if(!user){
            res.send("Email does not exist!")
        }
        const isPassworldValid=require('bcrypt').compareSync(req.body.password,user.hashedPassword)
        if(!isPassworldValid){
            res.send("password wrong")
        }
        res.send("login success")


        // if(!passwordReg.test(password)){
        //     res.send('password must greater than 8 bits')
        // }else{
        //     userModel.findOne({email,hashedPassword},(err,data)=>{
        //         if(err){
        //             console.log(err)
        //             res.send("Something wrong")
        //             return 
        //         }
        //         if(data){
        //             res.send("you have login!")
        //             //  res.redirect('/custtask.html')
        //             return 
        //         }
        //             res.send("email or password wrong")
                
        //     })

        //  const user=userModel.find(user=>user.email === req.body.email)
        //     if(user==null){
        //        return res.send("Email does not exit!")
        //     }
        //     try{
        //     if(await bcrypt.compare(req.body.password,user.password)){
        //         res.send("login success")
        //     }else{
        //         res.send("password incorrect")
        //     }
        // }catch{
        //     res.status(500).send('server error')
        // }


        }
         

    )
    app.listen(3000,function(request,response){
      console.log("Server is running on port 3000")
    })
},
    (err)=>{
        console.log(err)
    })

