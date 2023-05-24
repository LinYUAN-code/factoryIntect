<script setup>
import Avatar from "../components/Avatar.vue";
import { Plus } from "@element-plus/icons";
import { onMounted, ref } from "vue";
const myName = "刘志鹏";
const friends = [
  {
    name: "王刚",
    lastMsgTime: "11:45",
  },
  {
    name: "赵老",
    lastMsgTime: "15:35",
  },
  {
    name: "刘晶",
    lastMsgTime: "12:45",
  },
];

const chatMap = ref({
  王刚: {
    userName: "王刚",
    msgs: [
      { msg: "今天情况如何", time: "12:33", isMine: false },
      { msg: "最近的一批零件检测的如何了?", time: "12:33", isMine: false },
    ],
  },
  赵老: {
    userName: "赵老",
    msgs: [{ msg: "早上好", time: "12:33", isMine: false }],
  },
  刘晶: {
    userName: "刘晶",
    msgs: [{ msg: "一起去喝下午茶嘛？", time: "12:33", isMine: false }],
  },
});
const currentChatName = ref("王刚");
const changeUser = (name) => {
  currentChatName.value = name;
};

const fileList = ref([]);
const handleRemove = (file, fileList) => {
  console.log(file, fileList);
};
const handlePreview = (file) => {
  console.log(file);
};

const fileInputRef = ref();
const handleFileClick = () => {
  fileInputRef.value.click();
};
const uploadFileList = ref([]);
onMounted(() => {
  fileInputRef.value.addEventListener("change", function () {
    const files = fileInputRef.value.files;
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      uploadFileList.value.push({
        name: file.name,
        size: file.size,
      });
    }
  });
});
const handleRemoveFile = (index) => {
  uploadFileList.value.splice(index, 1);
};

const result = ref("暂时没有结果，请上传文件后点击识别零件");
const handleGenerateResult = () => {
  result.value = "Ring: 20 Nut: 23 Screu: 45";
};

const msgInput = ref("");
const handleSendMsg = () => {
  const now = new Date(); // 获取当前时间
  const hours = now.getHours().toString().padStart(2, "0"); // 获取当前小时，并格式化为两位数
  const minutes = now.getMinutes().toString().padStart(2, "0"); // 获取当前分钟，并格式化为两位数
  const timeString = `${hours}:${minutes}`; // 将小时和分钟拼接成 "12:23" 的形式
  chatMap.value[currentChatName.value].msgs.push({
    msg: msgInput.value,
    time: timeString,
    isMine: true,
  });
  msgInput.value = "";
};
</script>
 
<template>
  <div class="home-container">
    <div class="user-container">
      <div class="my-container">
        <div class="info-container">
          <Avatar :name="myName"></Avatar>
          <div class="name">{{ myName }}</div>
        </div>
        <div class="fcm mb10 mt10">
          当前状态：<span class="inline-dot"></span>在线
        </div>
        <!-- <div class="fcm mb10">已经处理的工件批次: 10</div> -->
        <!-- <el-button class="re-btn" type="primary">查看历史分析工件</el-button> -->
      </div>
      <div class="friend-container">
        <div
          class="info-container list-info-container"
          v-for="user in friends"
          :key="user.name"
          :class="{ active: currentChatName === user.name }"
          @click="changeUser(user.name)"
        >
          <Avatar :name="user.name"></Avatar>
          <div class="name">{{ user.name }}</div>
          <div class="info-time-containter">
            {{ user.lastMsgTime }}
          </div>
        </div>
      </div>
    </div>
    <div class="chat-container">
      <div class="msg-header-container">
        <div class="info-container">
          {{ chatMap[currentChatName].userName }}
          <Avatar :name="chatMap[currentChatName].userName"></Avatar>
          <div class="name">{{ chatMap[currentChatName].userName }}</div>
        </div>
      </div>
      <div class="msg-show-container">
        <div v-for="(msg, index) in chatMap[currentChatName].msgs" :key="index">
          <div class="left-msg" v-if="!msg.isMine">
            <div class="time">
              {{ msg.time }}
            </div>
            <div class="content">
              <div class="Avatar">
                <Avatar :name="chatMap[currentChatName].userName"></Avatar>
              </div>
              <div class="msg">
                <div class="name">
                  {{ chatMap[currentChatName].userName }}
                </div>
                <div class="msg">{{ msg.msg }}</div>
              </div>
            </div>
          </div>
          <div class="right-msg" v-if="msg.isMine">
            <div class="time">
              {{ msg.time }}
            </div>
            <div class="content">
              <div class="msg">
                <div class="msg">{{ msg.msg }}</div>
              </div>
              <div class="Avatar">
                <Avatar :name="myName"></Avatar>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="msg-send-container">
        <el-input
          type="textarea"
          :rows="8"
          placeholder="请输入内容"
          v-model="msgInput"
        ></el-input>
        <el-button class="re-btn" type="primary" @click="handleSendMsg"
          >发送</el-button
        >
      </div>
    </div>
    <div class="handle-container">
      <div class="upload-container">
        <form>
          <input
            type="file"
            name="upload"
            id="upload"
            style="display: none"
            ref="fileInputRef"
            multiple
          />
          <el-button size="default" type="primary" @click="handleFileClick"
            >点击上传</el-button
          >
        </form>
        <div v-for="(item, index) in uploadFileList" :key="index" class="file">
          {{ item.name }}
          <el-button
            size="default"
            type="danger"
            @click="handleRemoveFile(index)"
            >删除</el-button
          >
        </div>
      </div>
      <div class="result-container">
        <div class="result">{{ result }}</div>
        <div class="btn-container">
          <el-button size="default" type="primary" @click="handleGenerateResult"
            >识别零件</el-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<style lang='scss' scoped>
.active {
  background-color: #f2f2f2;
}
.file {
  margin-top: 10px;
  padding: 5px 5px;
  border: 1px solid rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.handle-container {
  flex: 1;
  border-left: 0.5px solid rgba(0, 0, 0, 0.5);
  .upload-container {
    height: 500px;
    border-bottom: 0.5px solid rgba(0, 0, 0, 0.5);
    padding: 20px 15px;
    overflow: scroll;
    .avatar-uploader .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
      border-color: #409eff;
    }
    .avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 178px;
      height: 178px;
      line-height: 178px;
      text-align: center;
    }
    .avatar {
      width: 178px;
      height: 178px;
      display: block;
    }
  }
  .result-container {
    .result {
      margin-top: 100px;
      margin-left: 300px;
      font-size: 20px;
      // font-family: "STXingkai";
    }
    .btn-container {
      display: flex;
      justify-content: flex-end;
      margin-top: 5%;
      margin-right: 4%;
    }
  }
}
.list-info-container {
  padding: 10px 10px;
  &:hover {
    background-color: #f2f2f2;
    cursor: pointer;
  }
}
.info-time-containter {
  position: absolute;
  right: 10px;
  font-size: 14px;
  font-weight: 300;
}
.mt10 {
  margin-top: 10px;
}
.mb10 {
  margin-bottom: 10px;
}
.frm {
  display: flex;
  justify-content: center;
}
.fcm {
  display: flex;
  align-items: center;
}
.info-container {
  display: flex;
  justify-content: left;
  align-items: center;
  position: relative;
  .name {
    margin-left: 10px;
  }
}
.home-container {
  width: 100vw;
  height: 100vh;
  overflow: hidden;
  display: flex;
  flex-direction: row;
}
.user-container {
  display: flex;
  flex-direction: column;
  width: 300px;
  height: 100vh;
  .my-container {
    padding: 10px;
    background-image: url("/b3.jpg");
    background-repeat: no-repeat;
    background-size: cover;
    border-bottom: 2px solid rgba(0, 0, 0, 0.5);
  }
  .friend-container {
    overflow: scroll;
    flex: 1;
  }
}
.chat-container {
  // background-color: blue;
  width: 500px;
  display: flex;
  flex-direction: column;
  .msg-header-container {
    @extend .frm;
    padding: 10px 0;
    background: #ffefba; /* fallback for old browsers */
    background: -webkit-linear-gradient(
      to right,
      #ffefba,
      #ffffff
    ); /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(
      to right,
      #ffefba,
      #ffffff
    ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
    border-bottom: 0.5px solid rgba(0, 0, 0, 0.5);
  }
  .msg-show-container {
    height: 500px;
    border-bottom: 0.5px solid rgba(0, 0, 0, 0.5);
    overflow: scroll;
    position: relative;
    .left-msg {
      .time {
        @extend .frm;
        font-size: 14px;
        font-weight: 300;
        padding-top: 10px;
        margin: 0 auto;
      }
      .content {
        display: flex;
        padding: 0 5px;
        .Avatar {
          margin-right: 5px;
        }
        .msg {
          padding-top: 2px;
          padding-left: 10px;
          .name {
            font-size: 14px;
            margin-left: 8px;
          }
          .msg {
            margin-top: 5px;
            padding: 10px 10px;
            background-color: #e5e5e5;
            border-radius: 6px;
            position: relative;
          }
        }
      }
    }
    .right-msg {
      @extend .left-msg;
      display: flex;
      flex-direction: column;
      align-items: flex-end;
      .content {
        padding: 0 5px;
        .Avatar {
          margin-left: 5px;
        }
        .msg {
          padding-right: 10px;
          .name {
            margin-left: 50px;
          }
          .msg {
            &::after {
              left: 114px;
              border: 8px solid;
              border-color: transparent transparent transparent #e5e5e5;
            }
          }
        }
      }
    }
  }
  .msg-send-container {
    position: relative;
    padding: 5px 5px;
    flex: 1;
    button {
      position: absolute;
      right: 5px;
      bottom: 5px;
    }
  }
}
.inline-dot {
  display: inline-block;
  width: 15px;
  height: 15px;
  border-radius: 50%;
  background-color: #2ecc71;
  margin-right: 3px;
}
</style>