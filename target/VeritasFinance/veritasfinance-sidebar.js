/* VeritasFinance shared navigation - single, accessible implementation. */
(function () {
  'use strict';
  const excluded = ['login.html', 'register.html', 'index.html', 'adminLogin.html', 'adminDashboard.html'];
  const file = window.location.pathname.split('/').pop() || 'dashboard.html';
  if (excluded.includes(file) || document.documentElement.dataset.vfNavigationReady === 'true') return;
  document.documentElement.dataset.vfNavigationReady = 'true';

  const links = [
    ['dashboard.html', 'fa-house', 'Dashboard'],
    ['upload.html', 'fa-wallet', 'Financial Details'],
    ['viewAssets.html', 'fa-boxes-stacked', 'My Assets'],
    ['analytics.html', 'fa-chart-line', 'Financial Analytics'],
    ['loan.html', 'fa-credit-card', 'Loan Eligibility'],
    ['aichat.html', 'fa-robot', 'AI Financial Assistant'],
    ['frauddetection.html', 'fa-shield-halved', 'Fraud Detection'],
    ['trustscore.html', 'fa-chart-pie', 'Trust Score & Budget'],
    ['reports.html', 'fa-file-lines', 'Reports'],
    ['profile.html', 'fa-user', 'My Profile']
  ];

  // Remove only stale menu controls left by an older navigation script.
  document.querySelectorAll('.menu-toggle, .vf-topbar').forEach(el => el.remove());

  const topbar = document.createElement('header');
  topbar.className = 'vf-topbar';
  topbar.innerHTML = `
    <button class="menu-toggle" type="button" aria-label="Open navigation" aria-expanded="false">
      <i class="fa-solid fa-bars" aria-hidden="true"></i>
    </button>
    <a class="vf-brand" href="dashboard.html" aria-label="VeritasFinance home">
      <strong>VeritasFinance</strong><span>Privacy-First Financial Identity</span>
    </a>`;

  const overlay = document.createElement('div');
  overlay.className = 'vf-nav-overlay';
  overlay.setAttribute('aria-hidden', 'true');

  const sidebar = document.createElement('aside');
  sidebar.className = 'vf-sidebar';
  sidebar.id = 'vfSidebar';
  sidebar.setAttribute('aria-label', 'Main navigation');
  sidebar.innerHTML = `
    <div class="vf-sidebar-header">
      <div><strong>VeritasFinance</strong><span>Your financial workspace</span></div>
      <button class="vf-close" type="button" aria-label="Close navigation">&times;</button>
    </div>
    <nav class="vf-nav" aria-label="Application pages"></nav>
    <a class="vf-logout" href="logout"><i class="fa-solid fa-right-from-bracket"></i><span>Log out</span></a>`;

  const nav = sidebar.querySelector('.vf-nav');
  links.forEach(([href, icon, label]) => {
    const a = document.createElement('a');
    a.href = href;
    if (file === href) { a.classList.add('active'); a.setAttribute('aria-current', 'page'); }
    a.innerHTML = `<i class="fa-solid ${icon}" aria-hidden="true"></i><span>${label}</span>`;
    nav.appendChild(a);
  });

  const toggle = topbar.querySelector('.menu-toggle');
  function close() {
    sidebar.classList.remove('is-open'); overlay.classList.remove('is-visible');
    toggle.setAttribute('aria-expanded', 'false'); document.body.classList.remove('vf-nav-open');
  }
  function open() {
    sidebar.classList.add('is-open'); overlay.classList.add('is-visible');
    toggle.setAttribute('aria-expanded', 'true'); document.body.classList.add('vf-nav-open');
    sidebar.querySelector('.vf-close').focus();
  }
  toggle.addEventListener('click', () => sidebar.classList.contains('is-open') ? close() : open());
  sidebar.querySelector('.vf-close').addEventListener('click', close);
  overlay.addEventListener('click', close);
  document.addEventListener('keydown', e => { if (e.key === 'Escape') close(); });

  document.body.classList.add('vf-auth-page');
  document.body.prepend(topbar);
  document.body.append(overlay, sidebar);
})();
