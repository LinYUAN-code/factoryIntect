import axios from "axios";

const instance = axios.create({
  baseURL: "http://localhost:8080/",
  timeout: 2000,
  headers: { "X-Custom-Header": "foobar" },
});

export async function login(data) {
  return await instance.post("/user/login", data);
}

export async function registe(data) {
  return await instance.post("/user/registe", data);
}

export async function sendMsg(data) {
  return await instance.post("/chat/sendMsg", data, {
    headers: {
      name: encodeURIComponent(localStorage.getItem("name")),
      token: localStorage.getItem("token"),
    },
  });
}

export async function getMsgList(data) {
  return await instance.post("/chat/getMsgList", data, {
    headers: {
      name: encodeURIComponent(localStorage.getItem("name")),
      token: localStorage.getItem("token"),
    },
  });
}

export async function getAllUserExceptName(data) {
  return await instance.post("/user/getAllUserName", data, {
    headers: {
      name: encodeURIComponent(localStorage.getItem("name")),
      token: localStorage.getItem("token"),
    },
  });
}
