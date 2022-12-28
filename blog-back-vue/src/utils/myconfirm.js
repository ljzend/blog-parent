import { MessageBox, Message } from 'element-ui'
//删除弹框
export default function myconfirm(text) {
    return MessageBox.confirm(text, '系统提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
}
