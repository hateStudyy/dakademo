const api = axios.create({
  baseURL: 'http://localhost:8111',
});

// 获取打卡列表
async function fetchDakaList() {
  try {
    const res = await api.get('/api/daka/list');
    const list = res.data || [];
    const ul = document.getElementById('dakaList');
    ul.innerHTML = '';
    list.forEach(item => {
      const li = document.createElement('li');
      li.textContent = `${item.description}（用户ID：${item.userId}）`;
      ul.appendChild(li);
    });
  } catch (err) {
    console.error('获取打卡失败', err);
  }
}

// 添加打卡记录
async function addDaka() {
  const userId = document.getElementById('userId').value;
  const description = document.getElementById('description').value;

  if (!userId || !description) {
    alert('请填写完整信息');
    return;
  }

  try {
    const res = await api.post('/api/daka/add?userId=' + userId, { description })
    alert(JSON.stringify(res.data));
    fetchDakaList();
  } catch (err) {
    console.error('打卡失败', err);
    alert('打卡失败，请查看控制台错误');
  }
}

// 页面加载时自动获取列表
window.onload = fetchDakaList;
