//引入nodemailer
var nodemailer=require('nodemailer')
//var smtpTransport = require('nodemailer-smtp-transport');
// 创建一个SMTP客户端配置
var config = {
        host: 'smtp.163.com', 
        port: 465,
        secure:true,
        auth: {
            user: 'g13546576777@163.com', //刚才注册的邮箱账号
            pass: 'PQPFUPSNNAIPQJCZ'  //邮箱的授权码，不是注册时的密码
        }
    };

// 创建一个SMTP客户端对象
var transporter = nodemailer.createTransport(config);

// 发送邮件
module.exports = function (mail){
    transporter.sendMail(mail, function(error, info){
        if(error) {
            return console.log(error);
        }
        console.log('mail sent:', info.response);
    });
};