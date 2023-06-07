export function checkLogin() {
  return localStorage.getItem("name") && localStorage.getItem("token");
}
