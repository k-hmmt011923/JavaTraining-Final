# JavaTraining-Week6
## 機能概要

- Spring MVC（Controller / Service / Repository）と Thymeleaf を用いて、タスク管理アプリケーションを実装しました。
一覧表示・新規作成・編集・削除・完了切替のCRUD機能を画面から操作できます。

- バリデーションエラー表示、PRGパターン（Post-Redirect-Get）、フラッシュメッセージ表示、存在しないIDアクセス時の404ページ表示にも対応しています。

## 起動手順

### 必要環境
・JDK 21
・Gradle Wrapper

プロジェクト直下で以下を実行してください。

- ./gradlew bootRun

Windows環境で上記が実行できない場合

- gradlew.bat bootRun

起動後、ブラウザで以下にアクセスしてください。

- http://localhost:8080/tasks

## 画面遷移 / URL一覧

- GET /tasks
タスク一覧画面を表示します。完了状態も表示されます。

- GET /tasks/new
新規作成フォームを表示します。

- POST /tasks
タスクを登録します。成功時は /tasks にリダイレクトされ、フラッシュメッセージが表示されます。

- GET /tasks/{id}/edit
編集フォームを表示します。

- POST /tasks/{id}
タスクを更新します。成功時は /tasks にリダイレクトされます。

- POST /tasks/{id}/delete
タスクを削除します。成功時は /tasks にリダイレクトされ、メッセージが表示されます。

- POST /tasks/{id}/toggle
完了状態を切り替えます。成功時は /tasks にリダイレクトされます。

## バリデーション

タイトルは必須入力です。
タイトルは50文字以内で入力する必要があります。

バリデーションエラーが発生した場合はフォーム画面に戻り、入力欄の下にエラーメッセージを表示します。
th:errors を使用してフィールド単位でメッセージを表示しています。

## 例外ハンドリング

存在しないIDにアクセスした場合、TaskNotFoundException を発生させます。
@ControllerAdvice を使用して例外を捕捉し、以下のページを表示します。

templates/error/404.html

HTTPステータスは 404 で返却されます。

## 実装上の工夫

・Entityと画面入力用DTO（TaskForm）を分離し、責務を明確にしました。
・共通レイアウト（layout.html）を作成し、ヘッダーとフラッシュメッセージを共通化しました。
・PRGパターンを用いて二重送信を防止しています。
・並び替え用にリクエストパラメータ（sort, direction）を受け取れる設計にしています。

## 任意機能の実装
- 検索機能

一覧画面に検索ボックスを設置し、キーワードを入力するとタイトルに一致するタスクのみを表示します。
検索条件はクエリパラメータとして受け取り、サーバー側でフィルタリングを行っています。

例
/tasks?keyword=買い物

上記の場合、「買い物」を含むタスクのみ表示されます。

## 並び替え機能（クエリパラメータ）

一覧画面では、クエリパラメータを利用して並び替えを行うことができます。

- 使用パラメータ
sort … 並び替え対象項目
direction … 並び順（asc または desc）

- /tasks?sort=id&direction=asc
ID昇順で表示

- /tasks?sort=id&direction=desc
ID降順で表示

- /tasks?sort=title&direction=asc
タイトル昇順で表示

- /tasks?sort=completed&direction=desc
完了状態で降順表示

- コントローラでは @RequestParam を用いてパラメータを受け取り、
Service 層で Spring Data JPA の Sort 機能を利用して並び替えを実現しています。
