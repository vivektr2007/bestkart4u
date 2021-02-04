
<script type="text/javascript" src="./adminMenu/dmenu.js"></script>
 <link type="text/css" href="./adminMenu/style.css" rel="stylesheet"></link>
 <script type="text/javascript" src="./adminMenu/data.js"></script>
 
 <style>
	*{margin: 0px;
		padding:0px}
	</style> 
	
<ul id="blueMenu">
	<li class="istylei0"><a href="#"><span>Product Section</span></a>
	<ul class="istylem0">
		<!-- <li><a href="#"><b>Product Info</b></a></li> -->
		<li><a href="javascript:void();"><span>Add</span></a>
			<ul>
				<li><a href="addMainCategory.jsp">Add Main Category</a></li>
				<li><a href="GetSubcategoryForm.action">Add Sub Category</a></li>
				<li><a href="GetChildcategoryForm.action">Add Child Category</a></li>
				<li><a href="GetProductForm.action">Add Product</a></li>
				<li><a href="GetProductVariantForm.action">Add Product Variants</a></li>
			</ul>
		</li>
		
		<li><a href="javascript:void();"><span>Edit</span></a>
			<ul>
				<li><a href="EditCategoryForm.action">Edit Category</a></li>
				<li><a href="EditSubCategoryForm.action">Edit Sub Category</a></li>
				<li><a href="EditChildCategoryForm.action">Edit Child Category</a></li>
				<li><a href="GetAllProductForm.action">Edit Product</a></li>
			</ul>
		</li>

		<li><a href="javascript:void();"><span>Delete</span></a>
			<ul>
				<li><a href="DeleteCategoryForm.action">Delete Category</a></li>
				<li><a href="DeleteSubCategoryForm.action">Delete Sub Category</a></li>
				<li><a href="DeleteChildCategoryForm.action">Delete Child Category</a></li>
				<li><a href="DeleteProductForm.action">Delete Product</a></li>
			</ul>
		</li>

	</ul>
	</li>
	
	<li class="istylei0"><a href="#"><span>Upload/Download Section</span></a>
	<ul class="istylem0">
		<!-- <li><a href="#"><b>Product Info</b></a></li> -->
		<li><a href="GetUploadProductForm.action"><span>Mass Add Product</span></a></li>
		<li><a href="GetUploadImageZipForm.action"><span>Mass upload All Image</span></a></li>
		<li><a href="GetMassUpdateProductForm.action"><span>Mass Update Product</span></a></li>
		<li><a href="DownloadAllProductForm.action"><span>Download All Products</span></a></li>
		<li><a href="DeleteAllUnusedImages.action"><span>Delete All unused Images</span></a></li>
		<li><a href="CheckAkkImages.action"><span>Resolve All Images</span></a></li>
	</ul>
	</li>
	
	<li class="istylei0"><a href="#"><span>Survey Section</span></a>
	<ul class="istylem1">
		<li><a href="#"><span>Create Section</span></a>
		<ul>
			<li><a href="GetSurveyForm.action">Create Survey</a></li>
			<li><a href="GetQuestionForm.action">Create Question</a></li>
			<li><a href="GetAnswersForm.action">Add Answers</a></li>
			<li><a href="ViewSurveyAction.action">View Survey</a></li>
		</ul>
		</li>
		<li><a href="#"><span>Update Section</span></a>
		<ul>
			<li><a href="GetUpdateSurveyForm.action">Update Survey</a></li>
			<li><a href="GetUpdateQuestionForm.action">Update Question</a></li>
			<li><a href="GetUpdateAnswerForm.action">Update Answers</a></li>
			<!-- <li><a href="GetAnswersForm.action">Activate/Deactivate Survey</a></li>
			<li><a href="GetAnswersForm.action">Activate Question</a></li>
			<li><a href="GetAnswersForm.action">Activate Answer</a></li> -->
		</ul>
		</li>
		<li><a href="#"><span>Delete Section</span></a>
		<ul>
			<li><a href="DeleteSurveyForm.action">Delete Survey</a></li>
			<li><a href="DeleteQuestionForm.action">Delete Question</a></li>
			<li><a href="DeleteAnswersForm.action">Delete Answers</a></li>
		</ul>
		</li>
		
		<!-- <li class="istylei1"><a href="#">&nbsp</a></li>
		<li class="istylei1"><a href="#">&nbsp</a></li>
		<li class="istylei1"><a href="#">&nbsp</a></li> -->
	</ul>
	</li>	
	
	<!-- <li class="istylei0"><a href="#">Download</a></li> -->
	<li class="istylei0"><a href="#"><span>Order Section</span></a>
	<ul>
		<li><a href="ViewOrderDetailAction.action">View Pending Orders</a></li>
		<li><a href="GetGenerateReceiptForm.action">Generate Invoice</a></li>
		<li><a href="GetGenerateCustomReceiptForm.action">Generate Custom Invoice</a></li>
		<li><a href="GetUpdatedDeliveredProductForm.action">Update For Delivered Products</a></li>
		<!-- <li><a href="#"><span>View</span></a>
		<ul>
			<li><a href="#">Non-profit License</a></li>
			<li><a href="#">Single Site License</a></li>
			<li><a href="#">Multiple Site License</a></li>
			<li><a href="#">Developer & Enterprise</a></li>
			<li class="istylei1"><a href="#">&nbsp</a></li>
		</ul>
		</li> -->
	</ul>
	</li>
	<li class="istylei0"><a href="#"><span>User Section</span></a>
	<ul>
		<li><a href="UpdatePasswordForm.action">Update Password</a></li>
		<!-- <li><a href="#"><span>View</span></a>
		<ul>
			<li><a href="#">Non-profit License</a></li>
			<li><a href="#">Single Site License</a></li>
			<li><a href="#">Multiple Site License</a></li>
			<li><a href="#">Developer & Enterprise</a></li>
			<li class="istylei1"><a href="#">&nbsp</a></li>
		</ul>
		</li> -->
	</ul>
	</li>
	
	<li class="istylei0"><a href="AdminLogout.action">Logout</a></li>
</ul>

<br/>
<br/>
<br/>

