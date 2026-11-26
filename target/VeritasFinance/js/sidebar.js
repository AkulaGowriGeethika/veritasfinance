/* =================================
   VERITASFINANCE COMMON SIDEBAR
================================= */

document.addEventListener("DOMContentLoaded", function () {

    /* CREATE MENU BUTTON */

    const menuButton = document.createElement("button");

    menuButton.className = "menu-toggle";

    menuButton.innerHTML = '<i class="fa-solid fa-bars"></i>';

    menuButton.setAttribute(
        "aria-label",
        "Open navigation menu"
    );

    document.body.appendChild(menuButton);


    /* CREATE SIDEBAR */

    const sidebar = document.createElement("aside");

    sidebar.className = "vf-sidebar";

    sidebar.id = "vfSidebar";


    sidebar.innerHTML = `

        <div class="vf-sidebar-header">

            <div class="vf-logo">

                <h2>VeritasFinance</h2>

                <p>Privacy-First Financial Identity</p>

            </div>

            <button
                class="vf-close"
                id="vfClose"
                aria-label="Close menu">

                ×

            </button>

        </div>


        <nav class="vf-nav">

            <a href="dashboard.html"
               data-page="dashboard.html">

                <i class="fa-solid fa-house"></i>

                <span>Dashboard</span>

            </a>


            <a href="upload.html"
               class="upload"
               data-page="upload.html">

                <i class="fa-solid fa-file-arrow-up"></i>

                <span>Financial Details</span>

            </a>


            <a href="analytics.html"
               class="analytics"
               data-page="analytics.html">

                <i class="fa-solid fa-chart-line"></i>

                <span>Financial Analytics</span>

            </a>


            <a href="loan.html"
               class="loan"
               data-page="loan.html">

                <i class="fa-solid fa-credit-card"></i>

                <span>Loan Eligibility</span>

            </a>


            <a href="aichat.html"
               class="chat"
               data-page="aichat.html">

                <i class="fa-solid fa-robot"></i>

                <span>AI Financial Assistant</span>

            </a>


            <a href="frauddetection.html"
               class="fraud"
               data-page="frauddetection.html">

                <i class="fa-solid fa-shield-halved"></i>

                <span>Fraud Detection</span>

            </a>


            <a href="trustscore.html"
               class="trust"
               data-page="trustscore.html">

                <i class="fa-solid fa-chart-pie"></i>

                <span>Trust Score</span>

            </a>


            <a href="budget.html"
               class="budget"
               data-page="budget.html">

                <i class="fa-solid fa-wallet"></i>

                <span>Budget Planner</span>

            </a>

        </nav>


        <div class="vf-logout">

            <a href="login.html">

                <i class="fa-solid fa-right-from-bracket"></i>

                <span>Logout</span>

            </a>

        </div>

    `;


    document.body.appendChild(sidebar);


    /* OVERLAY */

    const overlay = document.createElement("div");

    overlay.className = "vf-overlay";

    overlay.id = "vfOverlay";

    document.body.appendChild(overlay);


    /* ELEMENTS */

    const closeButton =
        document.getElementById("vfClose");


    /* OPEN SIDEBAR */

    function openSidebar() {

        sidebar.classList.add("active");

        overlay.classList.add("active");

    }


    /* CLOSE SIDEBAR */

    function closeSidebar() {

        sidebar.classList.remove("active");

        overlay.classList.remove("active");

    }


    /* MENU BUTTON */

    menuButton.addEventListener(
        "click",
        openSidebar
    );


    /* CLOSE BUTTON */

    closeButton.addEventListener(
        "click",
        closeSidebar
    );


    /* OVERLAY */

    overlay.addEventListener(
        "click",
        closeSidebar
    );


    /* ESC KEY */

    document.addEventListener(
        "keydown",
        function(event) {

            if (event.key === "Escape") {

                closeSidebar();

            }

        }
    );


    /* ACTIVE PAGE */

    const currentPage =
        window.location.pathname
        .split("/")
        .pop();


    const links =
        sidebar.querySelectorAll(
            ".vf-nav a"
        );


    links.forEach(function(link) {

        if (
            link.getAttribute("data-page")
            === currentPage
        ) {

            link.classList.add("active");

        }

    });

});