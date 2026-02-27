# JavaTraining-Week7

## 機能概要

- Spring Security を導入し、フォームログイン機能を実装しました。
- 認証されたユーザのみ/tasks/にアクセスできる構成とし、JUnit + MockMvc による統合テストで主要動作を確認しています。

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

### テスト実行方法

- ./gradlew test

## 認証機能

- ユーザ情報を H2 データベースに保存
- UserAccountエンティティを作成
- UserDetailsServiceを実装し DB 認証を実現
- パスワードはBCryptで暗号化
- 起動時に初期ユーザを登録（CommandLineRunner）

### ログイン方法

- username = testuser
- password = password

## Security設定

- /login と/css/ は未認証でもアクセス可能
- /tasks/は認証必須
- フォームログイン有効化
- ログイン成功後 /tasks に遷移
- ログアウト後 /login?logout に遷移

## ログイン画面

- templates/login.html
- CSRFトークン対応
- ログイン成功・失敗メッセージ表示
- ログアウト成功メッセージ表示

## MockMvcによる統合テスト

以下の主要動作を自動テストで確認しています：

- 認証なしで /tasks にアクセスすると /login にリダイレクトされる
- /login は未認証でも表示可能
- 認証済みユーザは/tasks を表示できる
- ログイン成功時 /tasks に遷移する
- ログイン失敗時 /login?error に遷移する

## 工夫した点

- 段階的に認証制御を実装し、「未認証では入れない → ログイン後は入れる」を一つずつ確認しながら構築しました。
- DB認証とBCryptによるパスワード暗号化を採用し、実務を意識した構成にしました。
- MockMvcによる自動テストを実装し、認証制御とログイン成功・失敗の挙動を機械的に検証できるようにしました。
