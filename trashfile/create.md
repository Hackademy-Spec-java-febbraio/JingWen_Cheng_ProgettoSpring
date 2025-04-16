<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:insert="~{index :: head}"></head>
<body>
    <div th:insert="~{index :: navbar}"></div>

    <div class="vh-100">
        <div class="container">
            <div class="row">
                <div class="col-12 my-5 d-flex justify-content-between align-items-center">
                    <h1 th:text="${title}">Inserisci un articolo</h1>
                </div>

                <div class="col-12 my-5">
                    <form th:action="@{/articles}" method="POST" th:object="${article}" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label for="title" class="form-label">Titolo</label>
                            <input id="title" type="text" th:field="*{title}" class="form-control" placeholder="Inserisci un titolo...">
                            <p th:errors="*{title}" class="text-danger" th:if="${#fields.hasErrors('title')}"></p>
                        </div>

                        <div class="mb-3">
                            <label for="subtitle" class="form-label">Sottotitolo</label>
                            <input id="subtitle" type="text" th:field="*{subtitle}" class="form-control" placeholder="Inserisci un sottotitolo...">
                            <p th:errors="*{subtitle}" class="text-danger" th:if="${#fields.hasErrors('subtitle')}"></p>
                        </div>

                        <div class="mb-3">
                            <label for="body" class="form-label">Articolo</label>
                            <textarea id="body" th:field="*{body}" class="form-control" placeholder="Inserisci il testo..."></textarea>
                            <p th:errors="*{body}" class="text-danger" th:if="${#fields.hasErrors('body')}"></p>
                        </div>

                        <div class="mb-3">
                            <label for="publishDate" class="form-label">Publish Date</label>
                            <input id="publishDate" type="date" th:field="*{publishDate}" class="form-control" placeholder="yyyy-mm-dd">
                            <p th:errors="*{publishDate}" class="text-danger" th:if="${#fields.hasErrors('publishDate')}"></p>
                        </div>

                        <div class="mb-3">
                            <label for="category" class="form-label">Categoria</label>
                            <select id="category" th:field="*{category}" class="form-select">
                                <option th:each="category : ${categories}" th:value="${category.id}" th:text="${category.name}">Categoria</option>
                            </select>
                            <p th:errors="*{category}" class="text-danger" th:if="${#fields.hasErrors('category')}"></p>
                        </div>

                        <div class="mb-3">
                            <label for="image" class="form-label">Immagine</label>
                            <input type="file" name="file-image" class="form-control">
                        </div>

                        <button type="submit" class="btn btn-success">Crea articolo</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div th:insert="~{index :: footer}"></div>
    <script th:replace="~{index :: bootstrapScript}"></script>
</body>
</html>
