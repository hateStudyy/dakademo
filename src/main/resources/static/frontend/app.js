const api = axios.create({
  baseURL: 'http://localhost:8111/api/',
});

// 获取打卡列表
async function fetchDakaList() {
  try {
    const res = await api.get('/daka/list');
    const list = res.data || [];
    const ul = document.getElementById('dakaList');
    const rawDat = document.getElementById('rawData');
    rawDat.textContent = JSON.stringify(list);
    ul.innerHTML = '';
    list.forEach(item => {
      const li = document.createElement('li');
      li.textContent = `id:${item.id} ${item.description}（用户ID：${item.userId}）（打卡时间：${item.createTime}）`;
      ul.appendChild(li);
    });
  } catch (err) {
    console.error('获取打卡失败', err);
  }
}


// 页面加载时自动获取列表
window.onload = fetchDakaList;

// 添加打卡记录
async function addDaka() {
  const userId = document.getElementById('userId').value;
  const description = document.getElementById('description').value;

  if (!userId || !description) {
    alert('请填写完整信息');
    return;
  }

  try {
    const res = await api.post('/daka/add?userId=' + userId, { description })
    alert(JSON.stringify(res.data));
    fetchDakaList();
  } catch (err) {
    console.error('打卡失败', err);
    alert('打卡失败，请查看控制台错误');
  }
}

// 获取当前时区
async function getTimezone() {
  try {
    const res = await api.get('/daka/timezone');
    document.getElementById('timezoneDisplay').textContent = res.data;
  } catch (err) {
    console.error('获取时区失败', err);
    document.getElementById('timezoneDisplay').textContent = '获取时区失败，请查看控制台';
  }
}

// 聊天框
document.addEventListener('DOMContentLoaded', function () {
  const chatForm = document.getElementById('chat-form');
  const chatInput = document.getElementById('chat-input');
  const chatMessages = document.getElementById('chat-messages');

  chatForm.addEventListener('submit', function (e) {
    e.preventDefault();
    const message = chatInput.value.trim();
    if (!message) return;

    addMessage(message, 'user');

    // 使用统一的 api 实例
    api.post('/daka/ai', { message })
        .then(response => {
          addMessage(response.data, 'ai');
        })
        .catch(error => {
          console.error('Error:', error);
          addMessage('发生错误，请重试。', 'system');
        });

    chatInput.value = '';
  });

  function addMessage(text, sender) {
    const messageElement = document.createElement('li');
    messageElement.textContent = text;
    messageElement.classList.add(sender);
    chatMessages.appendChild(messageElement);
    chatMessages.scrollTop = chatMessages.scrollHeight;
  }
});

// 获取 AI 分析结果（支持 ?userId=xxx 形式）
async function getAIAnalysis(userId) {
  if (!userId) {
    alert('用户ID不能为空');
    return;
  }

  const loading = document.getElementById('aiAnalysisLoading');
  const resultDiv = document.getElementById('aiAnalysisResult');

  loading.style.display = 'block';
  resultDiv.innerHTML = ''; // 清空旧内容

  try {
    const res = await api.get('/daka/aiAnalysis', {
      params: {
        userId: userId
      }
    });

    resultDiv.innerHTML = marked.parse(res.data); // 支持 Markdown 格式渲染
  } catch (err) {
    console.error('获取 AI 分析失败', err);
    resultDiv.textContent = '获取分析失败，请查看控制台错误';
  } finally {
    loading.style.display = 'none';
  }
}


// Markdown 编辑器逻辑
document.addEventListener('DOMContentLoaded', function () {
  const input = document.getElementById('markdownInput');
  const preview = document.getElementById('markdownPreview');

  // 初始化预览
  function updatePreview() {
    const markdownText = input?.value || '';
    preview.innerHTML = marked.parse(markdownText);
  }

  // 初始加载一次
  if (input && preview) {
    updatePreview();
    input.addEventListener('input', updatePreview);
  }
});









