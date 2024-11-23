export const date = ()=>{
    let hours = new Date().getHours();
    let msg = ""
    if (hours <= 9){
        msg = '早上'
    }else if (hours <= 14){
        msg = '上午'
    }else if (hours <= 18){
        msg = '下午'
    }else {
        msg = '晚上'
    }
    return msg
}

export  const  myDate = ()=> {
    let now = new Date();
    let year = now.getFullYear();
    let month = now.getMonth() + 1;
    let day = now.getDate();
    let hours = now.getHours();
    let minutes = now.getMinutes();
    let seconds = now.getSeconds();
    let arr_work = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
    let week = arr_work[ now.getDay()];
    return year + "年" + month + "月" + day + "日" +" " + week +"  "+ hours + ":" + minutes + ":" + seconds
}