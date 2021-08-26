//连接数据库
let mongoose=require('mongoose')
mongoose.set('useCreateIndex',true)
const DB_NAME='register'
const PORT=27017
const IP='localhost'
//mongodb://${IP}:${PORT}/${DB_NAME}
function connectMongo(success,failed){
    mongoose.connect('mongodb://127.0.0.1:27017/register',{
        useNewUrlParser:true,
        useUnifiedTopology:true})
    mongoose.connection.on('open',function(err){
    if(err){
        console.log('数据库连接失败',err)
       failed('connect failed')
    }else{
    console.log('数据库连接成功')
    success()
    }
})
    
}
module.exports=connectMongo