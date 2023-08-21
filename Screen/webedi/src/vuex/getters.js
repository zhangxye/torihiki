const getters = {
  sidebar: state => state.app.sidebar,
//  token: state => state.user.token,
//  avatar: state => state.user.avatar,
  user_id: state => state.user.user_id,
  account: state => state.user.account,
  username: state => state.user.username,
  password: state => state.user.password,
  dept_code: state => state.user.dept_code,
  dept_name: state => state.user.dept_name,
  address: state => state.user.address,
  phone: state => state.user.phone,
  principal: state => state.user.principal,
  email: state => state.user.email,
  business_scope: state => state.user.business_scope,
  manager_id: state => state.user.manager_id,
  islogin: state => state.user.islogin,
  roles: state => state.user.roles,
  user_type: state => state.user.user_type,
  token: state => state.user.token,
  permission_routers: state => state.permission.routers,
  addRouters: state => state.permission.addRouters
};
export default getters

