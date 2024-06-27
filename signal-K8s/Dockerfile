# final stage
FROM python:3.12.2-slim
LABEL maintainer="cch0124"


WORKDIR /app

RUN addgroup --gid 1001 --system app && \
    adduser --no-create-home --shell /bin/false --disabled-password --uid 1001 --system --group app

USER app

COPY main.py .

ENTRYPOINT ["python", "main.py"]