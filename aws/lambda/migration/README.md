# migration-lambda

## Developments

* python -m venv venv
* venv\Scripts\Activate.ps1
  ※ linux の場合 source venv\Scripts\activate

* (venv) python -m pip install --upgrade pip
* (venv) pip install -r dev-requirements.txt


## tox

* (venv) $ pip install tox
* (venv) $ tox  # run tests and check code with flake8
* (venv) $ tox -e format # run black
* (venv) $ tox -e lint # run linting with pylint and mypy

## メモ

```
pytest -n={利用CPU数}
       --cov={対象ディレクトリ}
       -cov-fail-under={カバレッジ率}
       -r={対象ファイル(a=all)} 
       --cov-report={出力場所}
(pytest) https://docs.pytest.org/en/latest/contents.html
(pytest-cov) https://pytest-cov.readthedocs.io/en/latest/readme.html

flake8 {対象ディレクトリ}
       --statistics {エラーと警告をカウント}
       ---max-line-length {最大許容行長}
(flake8) https://pypi.org/project/flake8/

xenon --max-absolute {ブロックに対する絶対しきい値]
      --max-modules {モジュールに対するしきい値}
      --max-average {平均複雑度のしきい値}
      ※ コードメトリクスの計測を行い、指定したランクより低い箇所が一つでもあれば "non-zero" を返す
```
