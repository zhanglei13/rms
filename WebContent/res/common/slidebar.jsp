<!-- #section:basics/sidebar -->
<div id="sidebar" class="sidebar                  responsive">
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="ace-icon fa fa-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="ace-icon fa fa-pencil"></i>
			</button>

			<!-- #section:basics/sidebar.layout.shortcuts -->
			<button class="btn btn-warning">
				<i class="ace-icon fa fa-users"></i>
			</button>

			<button class="btn btn-danger">
				<i class="ace-icon fa fa-cogs"></i>
			</button>

			<!-- /section:basics/sidebar.layout.shortcuts -->
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span>

			<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
		</div>
	</div>
	<!-- /.sidebar-shortcuts -->

	<ul class="nav nav-list">
		<li class=""><a href="#" class="dropdown-toggle"> <i
				class="menu-icon fa fa-desktop"></i> <span class="menu-text">Profile
			</span>
		</a> <b class="arrow"></b></li>

		<li class="active open"><a data-url="view/workload" href="index#view/workload">
				<i class="menu-icon fa fa-list"></i> <span class="menu-text">Personal
					Workload</span>
		</a> <b class="arrow"></b></li>

		<li class=""><a href="index#view/evaluation"> <i
				class="menu-icon fa fa-pencil-square-o"></i> <span class="menu-text">
					Evaluation </span>
		</a> <b class="arrow"></b></li>


		<li class=""><a href="#" class="dropdown-toggle"> <i
				class="menu-icon fa fa-list-alt"></i> <span class="menu-text">
					Workload Approval </span> <b class="arrow fa fa-angle-down"></b>
		</a> <b class="arrow"></b>

			<ul class="submenu">
				<li class=""><a href="profile.html"> <i
						class="menu-icon fa fa-caret-right"></i> IT Leader
				</a> <b class="arrow"></b></li>

				<li class=""><a href="inbox.html"> <i
						class="menu-icon fa fa-caret-right"></i> Line Manager
				</a> <b class="arrow"></b></li>
			</ul></li>

		<li class=""><a href="#" class="dropdown-toggle"> <i
				class="menu-icon fa fa-calendar"></i><span class="menu-text">
					Project Admin </span> <b class="arrow fa fa-angle-down"></b>
		</a><b class="arrow"></b>
			<ul class="submenu">
				<li class=""><a href="form-elements.html"> <i
						class="menu-icon fa fa-caret-right"></i>Add
				</a><b class="arrow"></b></li>
				<li class=""><a href="form-wizard.html"> <i
						class="menu-icon fa fa-calendar"></i>Check
				</a><b class="arrow"></b></li>
			</ul></li>

		<li class=""><a href="gallery.html" class="dropdown-toggle">
				<i class="menu-icon fa fa-picture-o"></i><span class="menu-text">Reporter</span>
				<b class="arrow fa fa-angle-down"></b>
		</a><b class="arrow"></b>
			<ul class="submenu">
				<li class=""><a href="profile.html"> <i
						class="menu-icon fa fa-caret-right"></i>Workload
				</a> <b class="arrow"></b></li>
				<li class=""><a href="profile.html"> <i
						class="menu-icon fa fa-caret-right"></i>Evaluation
				</a><b class="arrow"></b></li>
				<li class=""><a href="profile.html"> <i
						class="menu-icon fa fa-caret-right"></i>Others
				</a><b class="arrow"></b></li>
			</ul></li>


		<li class=""><a href="#" class="dropdown-toggle"> <i
				class="menu-icon fa fa-tag"></i><span class="menu-text">Information
			</span> <b class="arrow fa fa-angle-down"></b>
		</a><b class="arrow"></b>
			<ul class="submenu">
				<li class=""><a href="profile.html"> <i
						class="menu-icon fa fa-caret-right"></i>Notification
				</a><b class="arrow"></b></li>
			</ul></li>
		<li class=""><a href="#" class="dropdown-toggle"> <i
				class="menu-icon fa fa-file-o"></i><span class="menu-text">
					Others </span> <b class="arrow fa fa-angle-down"></b>
		</a><b class="arrow"></b>
			<ul class="submenu">
				<li class=""><a href="profile.html"> <i
						class="menu-icon fa fa-caret-right"></i>Template
				</a><b class="arrow"></b></li>
			</ul></li>
	</ul>
	<!-- /.nav-list -->

	<!-- #section:basics/sidebar.layout.minimize -->
	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i class="ace-icon fa fa-angle-double-left"
			data-icon1="ace-icon fa fa-angle-double-left"
			data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>

	<!-- /section:basics/sidebar.layout.minimize -->
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}
	</script>
</div>